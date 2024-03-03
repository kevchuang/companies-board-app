package io.kevchuang.reviewboard.domain

import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.zioJson.given
import zio.json.*
import io.github.iltotore.iron.*
import io.kevchuang.reviewboard.domain.error.DomainError
import io.kevchuang.reviewboard.types.{NotEmpty, OnlyLetters}
import sttp.tapir.Schema
import sttp.tapir.generic.auto.*

object company:
  opaque type CompanyId = Long :| Positive
  object CompanyId extends RefinedTypeOps[Long, Positive, CompanyId]

  opaque type CompanyName <: String :| Alphanumeric = String :| Alphanumeric
  object CompanyName extends RefinedTypeOps[String, Alphanumeric, CompanyName]

  opaque type CompanySlug = String :| NotEmpty
  object CompanySlug extends RefinedTypeOps[String, NotEmpty, CompanySlug]

  opaque type CountryName = String :| OnlyLetters
  object CountryName extends RefinedTypeOps[String, OnlyLetters, CountryName]

  opaque type IndustryName = String :| OnlyLetters
  object IndustryName extends RefinedTypeOps[String, OnlyLetters, IndustryName]

  opaque type LocationName = String :| OnlyLetters
  object LocationName extends RefinedTypeOps[String, OnlyLetters, LocationName]

  private type TagNameConstraint = NotEmpty & LettersLowerCase
  opaque type TagName            = String :| TagNameConstraint
  object TagName extends RefinedTypeOps[String, TagNameConstraint, TagName]

  opaque type Url = String :| ValidURL
  object Url extends RefinedTypeOps[String, ValidURL, Url]

  final case class Company(
      id: CompanyId,
      slug: CompanySlug,
      name: CompanyName,
      url: Url,
      location: Option[LocationName] = None,
      country: Option[CountryName] = None,
      industry: Option[IndustryName] = None,
      image: Option[Url] = None,
      tags: List[TagName] = List.empty[TagName]
  )
  object Company:
    given JsonCodec[Company]     = DeriveJsonCodec.gen[Company]
    inline given Schema[Company] = Schema.derived[Company]
  end Company

  final case class CreateCompany(
      name: CompanyName,
      url: Url,
      location: Option[LocationName] = None,
      country: Option[CountryName] = None,
      industry: Option[IndustryName] = None,
      image: Option[Url] = None,
      tags: List[TagName] = List.empty[TagName]
  )
  object CreateCompany:
    given JsonCodec[CreateCompany]     = DeriveJsonCodec.gen[CreateCompany]
    inline given Schema[CreateCompany] = Schema.derived[CreateCompany]
  end CreateCompany

  sealed trait CompanyDomainError extends DomainError
  final case class CompanyAlreadyExists(companyName: CompanyName)
      extends CompanyDomainError
  object CompanyAlreadyExists:
    given JsonCodec[CompanyAlreadyExists] =
      DeriveJsonCodec.gen[CompanyAlreadyExists]
  end CompanyAlreadyExists

  final case class UnableToGenerateCompanyId(message: String)
      extends CompanyDomainError
  object UnableToGenerateCompanyId:
    given JsonCodec[UnableToGenerateCompanyId] =
      DeriveJsonCodec.gen[UnableToGenerateCompanyId]
  end UnableToGenerateCompanyId
end company
