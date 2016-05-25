package github4s.api

import github4s.GithubResponses.GHResponse
import github4s.free.domain.{ Pagination, Commit, Repository, User }
import github4s.{ GithubApiConfig, Decoders, HttpClient }
import io.circe.generic.auto._

/** Factory to encapsulate calls related to Repositories operations  */
class Repos(implicit config: GithubApiConfig) {

  import Decoders._

  val httpClient = new HttpClient

  /**
    * Get information of a particular repository
    *
    * @param accessToken to identify the authenticated user
    * @param owner of the repo
    * @param repo name of the repo
    * @return GHResponse[Repository] repository details
    */
  def get(accessToken: Option[String] = None, owner: String, repo: String): GHResponse[Repository] =
    httpClient.get[Repository](accessToken, s"repos/$owner/$repo")

  /**
    * Retrieve the list of commits for a particular repo
    *
    * @param accessToken to identify the authenticated user
    * @param owner of the repo
    * @param repo name of the repo
    * @param sha branch to start listing commits from
    * @param path commits containing this file path will be returned
    * @param author GitHub login or email address by which to filter by commit author.
    * @param since Only commits after this date will be returned
    * @param until Only commits before this date will be returned
    * @param pagination Limit and Offset for pagination
    * @return GHResponse[List[Commit]\] List of commit's details
    */
  def listCommits(
    accessToken: Option[String] = None,
    owner: String,
    repo: String,
    sha: Option[String] = None,
    path: Option[String] = None,
    author: Option[String] = None,
    since: Option[String] = None,
    until: Option[String] = None,
    pagination: Option[Pagination] = Some(httpClient.defaultPagination)
  ): GHResponse[List[Commit]] =

    httpClient.get[List[Commit]](accessToken, s"repos/$owner/$repo/commits", Map(
      "sha" → sha,
      "path" → path,
      "author" → author,
      "since" → since,
      "until" → until
    ).collect {
        case (key, Some(value)) ⇒ key → value
      }, pagination)

}