/*
 * Copyright 2016-2021 47 Degrees Open Source <https://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github4s.unit

import cats.effect.IO
import cats.syntax.either._
import github4s.GHResponse
import github4s.domain._
import github4s.interpreters.OrganizationsInterpreter
import github4s.utils.BaseSpec

class OrganizationsSpec extends BaseSpec {

  "Organization.listMembers" should "call to httpClient.get with the right parameters" in {

    val response: IO[GHResponse[List[User]]] =
      IO(GHResponse(List(user).asRight, okStatusCode, Map.empty))

    implicit val httpClientMock = httpClientMockGet[List[User]](
      url = s"orgs/$validRepoOwner/members",
      response = response
    )

    val organizations = new OrganizationsInterpreter[IO]

    organizations.listMembers(validRepoOwner, None, None, None, headerUserAgent)
  }

  "Organization.listOutsideCollaborators" should "call to httpClient.get with the right parameters" in {
    val response: IO[GHResponse[List[User]]] =
      IO(GHResponse(List(user).asRight, okStatusCode, Map.empty))

    implicit val httpClientMock = httpClientMockGet[List[User]](
      url = s"orgs/$validOrganizationName/outside_collaborators",
      response = response
    )

    val organizations = new OrganizationsInterpreter[IO]

    organizations.listOutsideCollaborators(validOrganizationName, None, None, headerUserAgent)
  }

}
