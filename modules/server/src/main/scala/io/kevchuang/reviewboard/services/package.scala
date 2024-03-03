package io.kevchuang.reviewboard

import io.kevchuang.reviewboard.services.company.CompanyService
import io.kevchuang.reviewboard.services.health.HealthService

package object services:
  type Services = CompanyService & HealthService
end services
