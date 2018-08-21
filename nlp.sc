import java.util.Properties

import $ivy.`edu.stanford.nlp:stanford-corenlp:3.9.1`
import ammonite.ops._
import coursier.maven.MavenRepository

interp.repositories() ++= Seq(MavenRepository(
  "http://maven.aliyun.com/nexus/content/repositories/central/",
))

val models = coursier.Dependency(
  coursier.Module("edu.stanford.nlp", "stanford-corenlp"),
  "3.9.1",
  attributes = coursier.Attributes(classifier = "models")
)

val zhModels = coursier.Dependency(
  coursier.Module("edu.stanford.nlp", "stanford-corenlp"),
  "3.9.1",
  attributes = coursier.Attributes(classifier = "models-chinese")
)
interp.load.ivy(models, zhModels)

import edu.stanford.nlp.simple.Sentence
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP


val s = new Sentence("I like the one")
println(s.words)

val props = new Properties()
props.load(s.getClass.getResourceAsStream("/StanfordCoreNLP-chinese.properties"))
val text = "克林顿说，华盛顿将逐步落实对韩国的经济援助。金大中对克林顿的讲话报以掌声：克林顿总统在会谈中重申，他坚定地支持韩国摆脱经济危机。"
println(new Sentence(text, props).words)

//val document = new Annotation(text)
//val corenlp = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties")
//corenlp.annotate(document)