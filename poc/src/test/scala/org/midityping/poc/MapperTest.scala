package poc.src.test.scala.org.midityping.poc

import org.midityping.poc._
import org.specs2.mutable.SpecificationWithJUnit

class MapperTest extends SpecificationWithJUnit {
  "Mapper" should {
    "map NoteOn event to KeyPress action" in {
      val m = Mapping(Map(
        EventDescriptor(EventType.MidiNoteOn, "85") -> ActionDescriptor(ActionType.KeyPress, "a")
      ))
      val mapper = new Mapper(m)
      mapper.getActionFor(MidiEvent(Note("C4", 85))) === Some(ActionDescriptor(ActionType.KeyPress, "a"))
    }
    "map NoteOn event to KeyPress action using mapping file" in {
      val mappingLoader = new JsonMappingLoader
      val mapper = new Mapper(mappingLoader.load("mapping.json"))
      mapper.getActionFor(MidiEvent(Note("C4", 85))) === Some(ActionDescriptor(ActionType.KeyPress, "a"))
    }
  }
}