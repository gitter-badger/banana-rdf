package org.w3.banana.bigdata

import org.scalatest._
import org.w3.banana._
import org.w3.banana.io.SparqlAnswerXml
import org.w3.banana.syntax.UriSyntaxTest


class BigdataOpsTest(implicit ops: RDFOps[Bigdata]) extends WordSpec with Matchers with OptionValues {

  type Rdf = Bigdata

  import ops._

  "(de)constructing plain literal" in {

    val plainLiteral: Rdf#Literal = Literal("Alexandre")

    val (lexicalForm, datatype, langOpt) = fromLiteral(plainLiteral)

    lexicalForm should be("Alexandre")

    datatype should be(xsd.string)

    langOpt should be(None)

  }

  "(de)constructing typed literal" in {

    val foaf = FOAFPrefix[Rdf]

    val typedLiteral: Rdf#Literal = Literal("Alexandre", foaf.name)

    val (lexicalForm, datatype, langOpt) = fromLiteral(typedLiteral)

    lexicalForm should be("Alexandre")

    datatype should be(foaf.name)

    langOpt should be(None)

  }

  "(de)constructing lang literal" in {

    val langLiteral: Rdf#Literal = Literal.tagged("Alexandre", Lang("fr"))

    val (lexicalForm, datatype, langOpt) = fromLiteral(langLiteral)

    lexicalForm should be("Alexandre")

    datatype should be(Literal.rdfLangString)

    langOpt.value should be("fr")

  }
}

class BigdataGraphTest extends GraphTest[Bigdata]

class BigdataMGraphTest extends MGraphTest[Bigdata]

import org.w3.banana.isomorphism._

object BigdataIsomorphismTest extends IsomorphismTest[Bigdata]

object BigdataGraphUnionTest extends GraphUnionTest[Bigdata]

class BigdataPointedGraphTest extends PointedGraphTest[Bigdata]

import org.w3.banana.diesel._

class BigdataDieselGraphConstructTest extends DieselGraphConstructTest[Bigdata]

class BigdataDieselGraphExplorationTest extends DieselGraphExplorationTest[Bigdata]

class BigdataDieselOwlPrimerTest extends DieselOwlPrimerTest[Bigdata]

import org.w3.banana.binder._

class BigdataCommonBindersTest extends CommonBindersTest[Bigdata]

class BigdataUriSyntaxTest extends UriSyntaxTest[Bigdata]

class BigdataSparqlGraphTest extends SparqlGraphTest[Bigdata, SparqlAnswerXml]()(
  Bigdata.ops,
  Bigdata.rdfXMLReader,
  Bigdata.sparqlOps,
  Bigdata.sparqlGraph,
  Bigdata.xmlSolutionsWriter,
  Bigdata.xmlQueryResultsReader)

class BigdataRecordBinderTest extends RecordBinderTest[Bigdata]

class BigdataCustomBinderTest extends CustomBindersTest[Bigdata]