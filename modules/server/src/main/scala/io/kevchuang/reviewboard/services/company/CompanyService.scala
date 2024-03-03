package io.kevchuang.reviewboard.services.company

import io.kevchuang.reviewboard.domain.company.*
import io.kevchuang.reviewboard.services.db.DatabaseService
import zio.*

trait CompanyService:
  def createCompany(
      company: CreateCompany
  ): ZIO[DatabaseService, CompanyDomainError, Company]
end CompanyService

object CompanyService:
  def createCompany(
      company: CreateCompany
  ): ZIO[DatabaseService & CompanyService, CompanyDomainError, Company] =
    ZIO.serviceWithZIO[CompanyService](_.createCompany(company))
end CompanyService
