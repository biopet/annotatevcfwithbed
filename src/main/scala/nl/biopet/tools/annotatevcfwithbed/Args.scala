package nl.biopet.tools.annotatevcfwithbed

import java.io.File

case class Args(inputFile: File = null,
                bedFile: File = null,
                outputFile: File = null,
                fieldName: String = null,
                fieldDescription: String = "",
                fieldType: String = "String")
