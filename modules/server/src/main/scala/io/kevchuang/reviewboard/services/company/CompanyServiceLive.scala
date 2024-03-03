package io.kevchuang.reviewboard.services.company

import io.kevchuang.reviewboard.domain.company.*
import io.kevchuang.reviewboard.services.db.DatabaseService
import zio.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.kevchuang.reviewboard.types.NotEmpty

object CompanyServiceLive:
  lazy val live: ULayer[CompanyService] = ZLayer.succeed(
    new CompanyService:
      private def generateCompanyId
          : ZIO[DatabaseService, UnableToGenerateCompanyId, CompanyId] =
        DatabaseService.countCompanies
          .flatMap: id =>
            ZIO
              .fromEither(id.refineEither[Positive])
              .mapBoth(
                error => UnableToGenerateCompanyId(error),
                companyId => CompanyId(companyId)
              )

      private def generateCompanySlug(
          companyName: CompanyName
      ): UIO[CompanySlug] =
        ZIO.succeed(
          CompanySlug(
            companyName
              .replaceAll(" +", " ")
              .split(" ")
              .map(_.toLowerCase)
              .mkString("-")
              .assume[NotEmpty]
          )
        )

      private def generateCompany(
          company: CreateCompany
      ): ZIO[DatabaseService, UnableToGenerateCompanyId, Company] =
        for
          companyId   <- generateCompanyId
          companySlug <- generateCompanySlug(company.name)
        yield Company(
          id = companyId,
          slug = companySlug,
          name = company.name,
          url = company.url,
          location = company.location,
          country = company.country,
          industry = company.industry,
          image = company.image,
          tags = company.tags
        )

      override def createCompany(
          company: CreateCompany
      ): ZIO[DatabaseService, CompanyDomainError, Company] =
        DatabaseService
          .existsCompany(company.name)
          .flatMap: exists =>
            if exists then ZIO.fail(CompanyAlreadyExists(company.name))
            else generateCompany(company).tap(DatabaseService.insertCompany)
  )
end CompanyServiceLive
