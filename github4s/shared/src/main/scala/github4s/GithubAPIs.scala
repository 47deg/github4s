/*
 * Copyright (c) 2016 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package github4s

import github4s.GithubResponses.{GHIO, GHResponse}
import github4s.app._
import github4s.free.algebra._
import github4s.free.domain._

class GHUsers(accessToken: Option[String] = None)(implicit O: UserOps[GitHub4s]) {

  def get(username: String): GHIO[GHResponse[User]] = O.getUser(username, accessToken)

  def getAuth: GHIO[GHResponse[User]] = O.getAuthUser(accessToken)

  def getUsers(since: Int, pagination: Option[Pagination] = None): GHIO[GHResponse[List[User]]] =
    O.getUsers(since, pagination, accessToken)

}

class GHRepos(accessToken: Option[String] = None)(implicit O: RepositoryOps[GitHub4s]) {

  def get(owner: String, repo: String): GHIO[GHResponse[Repository]] =
    O.getRepo(owner, repo, accessToken)

  def listCommits(
      owner: String,
      repo: String,
      sha: Option[String] = None,
      path: Option[String] = None,
      author: Option[String] = None,
      since: Option[String] = None,
      until: Option[String] = None,
      pagination: Option[Pagination] = None
  ): GHIO[GHResponse[List[Commit]]] =
    O.listCommits(owner, repo, sha, path, author, since, until, pagination, accessToken)

  def listContributors(
      owner: String,
      repo: String,
      anon: Option[String] = None
  ): GHIO[GHResponse[List[User]]] =
    O.listContributors(owner, repo, anon, accessToken)

}

class GHAuth(accessToken: Option[String] = None)(implicit O: AuthOps[GitHub4s]) {

  def newAuth(
      username: String,
      password: String,
      scopes: List[String],
      note: String,
      client_id: String,
      client_secret: String
  ): GHIO[GHResponse[Authorization]] =
    O.newAuth(username, password, scopes, note, client_id, client_secret)

  def authorizeUrl(
      client_id: String,
      redirect_uri: String,
      scopes: List[String]
  ): GHIO[GHResponse[Authorize]] =
    O.authorizeUrl(client_id, redirect_uri, scopes)

  def getAccessToken(
      client_id: String,
      client_secret: String,
      code: String,
      redirect_uri: String,
      state: String
  ): GHIO[GHResponse[OAuthToken]] =
    O.getAccessToken(client_id, client_secret, code, redirect_uri, state)
}

class GHGists(accessToken: Option[String] = None)(implicit O: GistOps[GitHub4s]) {
  def newGist(
      description: String,
      public: Boolean,
      files: Map[String, GistFile]
  ): GHIO[GHResponse[Gist]] =
    O.newGist(description, public, files, accessToken)
}

class GHIssues(accessToken: Option[String] = None)(implicit O: IssueOps[GitHub4s]) {

  def listIssues(
      owner: String,
      repo: String
  ): GHIO[GHResponse[List[Issue]]] =
    O.listIssues(owner, repo, accessToken)

  def searchIssues(
      query: String,
      searchParams: List[SearchParam]
  ): GHIO[GHResponse[SearchIssuesResult]] =
    O.searchIssues(query, searchParams, accessToken)
}