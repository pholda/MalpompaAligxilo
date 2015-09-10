package pl.pholda.malpompaaligxilo.googleapi

import java.io.File
import java.security.PrivateKey

sealed abstract class AccountConfig {
  def serviceAccountId: String
}

case class AccountConfigFile(
  serviceAccountId: String,
  p12PrivateKey: File
                              ) extends AccountConfig

case class AccountConfigKey(
  serviceAccountId: String,
  p12PrivateKey: PrivateKey
                               ) extends AccountConfig
