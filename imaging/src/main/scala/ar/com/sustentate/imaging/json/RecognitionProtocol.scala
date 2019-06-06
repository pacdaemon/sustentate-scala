package ar.com.sustentate.imaging.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import ar.com.sustentate.imaging.models.RecognitionRequest
import spray.json.DefaultJsonProtocol

trait RecognitionProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val recognitionRequestFormatter = jsonFormat2(RecognitionRequest.apply)
}
