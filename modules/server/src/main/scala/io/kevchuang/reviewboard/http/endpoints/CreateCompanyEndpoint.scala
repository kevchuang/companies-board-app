package io.kevchuang.reviewboard.http.endpoints

import io.kevchuang.reviewboard.domain.company.{*, given}
import io.kevchuang.reviewboard.services.company.CompanyService
import io.kevchuang.reviewboard.services.db.DatabaseService
import sttp.model.StatusCode
import sttp.tapir.*
import sttp.tapir.codec.iron.{*, given}
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.*
import zio.*

final case class CreateCompanyEndpoint()
    extends HttpEndpoint[
      DatabaseService & CompanyService,
      CreateCompany,
      CompanyDomainError,
      Company
    ]:
  def endpointDescription
      : ApiEndpoint[CreateCompany, CompanyDomainError, Company] =
    endpoint
      .tag("companies")
      .name("create")
      .description("create a listing for a company")
      .in("companies")
      .post
      .in(jsonBody[CreateCompany])
      .errorOut(
        oneOf[CompanyDomainError](
          oneOfVariant[CompanyAlreadyExists](
            StatusCode.BadRequest,
            jsonBody[CompanyAlreadyExists]
          ),
          oneOfVariant[UnableToGenerateCompanyId](
            StatusCode.BadRequest,
            jsonBody[UnableToGenerateCompanyId]
          )
        )
      )
      .out(jsonBody[Company])

  def endpointLogic: CreateCompany => ZIO[
    DatabaseService & CompanyService,
    CompanyDomainError,
    Company
  ] =
    CompanyService.createCompany
end CreateCompanyEndpoint

object CreateCompanyEndpoint:
  def make: UIO[CreateCompanyEndpoint] =
    ZIO.succeed(CreateCompanyEndpoint())
end CreateCompanyEndpoint
