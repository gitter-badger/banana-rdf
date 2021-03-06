package org.w3.banana.bigdata

import java.util.Properties

trait DefaultBigdataStore {

  lazy val journal="bigdata.jnl"
  /**
  BigData settings
    */
  lazy val properties:Properties = {
    val props = new Properties()
    props.setProperty("com.bigdata.rdf.store.AbstractTripleStore.quadsMode","true")
    props.setProperty("com.bigdata.rdf.store.AbstractTripleStore.textIndex","true")
    props.setProperty("com.bigdata.rdf.store.AbstractTripleStore.storeBlankNodes","true")

    props.setProperty("com.bigdata.rdf.sail.truthMaintenance","false")
    props.setProperty("com.bigdata.rdf.sail.statementIdentifiers","true")
    props.setProperty("com.bigdata.journal.AbstractJournal.file",journal)
    props
  }

}
