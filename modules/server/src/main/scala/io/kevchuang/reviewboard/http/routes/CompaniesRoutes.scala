package io.kevchuang.reviewboard.http.routes

import io.kevchuang.reviewboard.http.endpoints.CreateCompanyEndpoint
import io.kevchuang.reviewboard.services.company.CompanyService
import io.kevchuang.reviewboard.services.db.DatabaseService
import sttp.tapir.ztapir.ZServerEndpoint
import zio.*

private case class CompaniesRoutes(
    createCompanyEndpoint: CreateCompanyEndpoint
) extends HttpRoute[DatabaseService & CompanyService]:
  def endpoints: List[ZServerEndpoint[DatabaseService & CompanyService, Any]] =
    List(
      createCompanyEndpoint.serverEndpoint
    )
end CompaniesRoutes

object CompaniesRoutes:
  def make: UIO[CompaniesRoutes] =
    for createCompany <- CreateCompanyEndpoint.make
    yield CompaniesRoutes(createCompany)
end CompaniesRoutes
