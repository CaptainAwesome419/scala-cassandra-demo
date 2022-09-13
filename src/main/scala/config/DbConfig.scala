package com.babatunde
package config


import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.`type`.codec.TypeCodecs

import java.net.InetSocketAddress

case object DbConfig {

  val host = "127.0.0.1";
  val port = 9042;

  def getSession():CqlSession={
    CqlSession.builder()
      .withKeyspace("employee_db")
      .addContactPoint(new InetSocketAddress(host,port))
      .withLocalDatacenter("datacenter1")
      .addTypeCodecs(TypeCodecs.INT)
      .addTypeCodecs(TypeCodecs.BIGINT)
      .addTypeCodecs(TypeCodecs.DECIMAL)
      .build()
  }


}
