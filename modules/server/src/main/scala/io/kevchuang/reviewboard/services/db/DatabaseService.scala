package io.kevchuang.reviewboard.services.db

import io.kevchuang.reviewboard.domain.company.*
import zio.*

trait DatabaseService:
  def countCompanies: UIO[Long]
  def existsCompany(companyName: CompanyName): UIO[Boolean]
  def insertCompany(company: Company): UIO[Unit]
end DatabaseService

object DatabaseService:
  def countCompanies: URIO[DatabaseService, Long] =
    ZIO.serviceWithZIO[DatabaseService](_.countCompanies)

  def existsCompany(
      companyName: CompanyName
  ): URIO[DatabaseService, Boolean] =
    ZIO.serviceWithZIO[DatabaseService](_.existsCompany(companyName))

  def insertCompany(
      company: Company
  ): URIO[DatabaseService, Unit] =
    ZIO.serviceWithZIO[DatabaseService](_.insertCompany(company))
end DatabaseService
