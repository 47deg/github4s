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

package github4s

import github4s.domain._
import io.circe._
import io.circe.generic.auto._
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax._

object Encoders {

  implicit val encodeTreeData: Encoder[TreeData] = Encoder.instance {
    case d: TreeDataSha  => d.asJson
    case d: TreeDataBlob => d.asJson
  }

  implicit val encodeNewPullRequest: Encoder[CreatePullRequest] = Encoder.instance {
    case d: CreatePullRequestData  => d.asJson
    case d: CreatePullRequestIssue => d.asJson
  }

  implicit val encodePrrStatus: Encoder[PullRequestReviewState] =
    Encoder.encodeString.contramap(_.value)

  implicit val encodePrrEvent: Encoder[PullRequestReviewEvent] =
    Encoder.encodeString.contramap(_.value)

  implicit val encodeEditGistFile: Encoder[EditGistFile] = {
    deriveEncoder[EditGistFile].mapJsonObject(
      _.filter(e => !(e._1.equals("filename") && e._2.isNull))
    )
  }

  implicit val encoderCommiter: Encoder[Committer] = deriveEncoder[Committer]
  implicit val encoderDeleteFileRequest: Encoder[DeleteFileRequest] =
    deriveEncoder[DeleteFileRequest]
  implicit val encoderWriteFileContentRequest: Encoder[WriteFileRequest] =
    deriveEncoder[WriteFileRequest]
  implicit val encoderCreateReferenceRequest: Encoder[CreateReferenceRequest] =
    deriveEncoder[CreateReferenceRequest]
  implicit val encoderNewCommitRequest: Encoder[NewCommitRequest] = deriveEncoder[NewCommitRequest]
  implicit val encoderNewBlobRequest: Encoder[NewBlobRequest]     = deriveEncoder[NewBlobRequest]
  implicit val encoderNewTreeRequest: Encoder[NewTreeRequest]     = deriveEncoder[NewTreeRequest]
  implicit val encoderNewTagRequest: Encoder[NewTagRequest]       = deriveEncoder[NewTagRequest]
  implicit val encoderUpdateReferenceRequest: Encoder[UpdateReferenceRequest] =
    deriveEncoder[UpdateReferenceRequest]
  implicit val encoderSubscriptionRequest: Encoder[SubscriptionRequest] =
    deriveEncoder[SubscriptionRequest]
  implicit val encoderNewGistRequest: Encoder[NewGistRequest]     = deriveEncoder[NewGistRequest]
  implicit val encoderEditGistRequest: Encoder[EditGistRequest]   = deriveEncoder[EditGistRequest]
  implicit val encoderNewIssueRequest: Encoder[NewIssueRequest]   = deriveEncoder[NewIssueRequest]
  implicit val encoderEditIssueRequest: Encoder[EditIssueRequest] = deriveEncoder[EditIssueRequest]
  implicit val encoderLabel: Encoder[Label]                       = deriveEncoder[Label]
  implicit val encoderCommentData: Encoder[CommentData]           = deriveEncoder[CommentData]
  implicit val encoderNewReleaseRequest: Encoder[NewReleaseRequest] =
    deriveEncoder[NewReleaseRequest]
  implicit val encoderNewStatusRequest: Encoder[NewStatusRequest] = deriveEncoder[NewStatusRequest]
  implicit val encoderMilestoneData: Encoder[MilestoneData]       = deriveEncoder[MilestoneData]
  implicit val encodeNewPullRequestReview: Encoder[CreatePRReviewRequest] =
    deriveEncoder[CreatePRReviewRequest]
  implicit val encodeRequiestedReviewers: Encoder[ReviewersRequest] =
    deriveEncoder[ReviewersRequest]
}
