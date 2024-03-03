package io.kevchuang.reviewboard.services.db

import io.kevchuang.reviewboard.domain.company.*
import zio.*

import scala.collection.mutable

final case class InMemoryDatabase(database: mutable.Map[CompanyId, Company])
    extends DatabaseService:
  override def countCompanies: UIO[Long] =
    ZIO.succeed(database.size.toLong)

  override def existsCompany(companyName: CompanyName): UIO[Boolean] =
    ZIO.succeed(database.exists((_, c) => c.name == companyName))

  override def insertCompany(
      company: Company
  ): UIO[Unit] =
    ZIO.succeed(database.addOne(company.id -> company))
end InMemoryDatabase

object InMemoryDatabase:
  val live: ULayer[InMemoryDatabase] =
    ZLayer.succeed:
      InMemoryDatabase(mutable.Map.empty[CompanyId, Company])
end InMemoryDatabase
