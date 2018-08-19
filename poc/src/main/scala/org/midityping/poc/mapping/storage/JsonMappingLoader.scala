package org.midityping.poc.mapping.storage

import java.io._
import java.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.mapping.Mapping1

import scala.collection.JavaConverters._

object JsonMappingLoader {
  def load(file: File): Mapping1 = {
    load(new BufferedReader(new FileReader(file)))
  }

  def load(resourceFilename: String): Mapping1 = {
    val resource = getClass.getResourceAsStream(resourceFilename)
    load(new BufferedReader(new InputStreamReader(resource)))
  }

  private def load(reader: Reader): Mapping1 = {
    val gson = new GsonBuilder().create
    val mapType = new TypeToken[util.Map[String, ActionDescriptor]] {}.getType
    val map = gson.fromJson[util.Map[String, ActionDescriptor]](reader, mapType)
    Mapping1(map.asScala)
  }
}
