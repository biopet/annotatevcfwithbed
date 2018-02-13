/*
 * Copyright (c) 2014 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools.annotatevcfwithbed

import java.io.File

import nl.biopet.utils.tool.{AbstractOptParser, ToolCommand}

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {
  opt[File]('I', "inputFile") required () valueName "<vcf file>" action {
    (x, c) =>
      c.copy(inputFile = x)
  } text "Input VCF file. Mandatory field"
  opt[File]('B', "bedFile") required () valueName "<bed file>" action {
    (x, c) =>
      c.copy(bedFile = x)
  } text "Input Bed file. Mandatory field"
  opt[File]('o', "output") required () valueName "<vcf file>" action { (x, c) =>
    c.copy(outputFile = x)
  } text "Output VCF file. Mandatory field"
  opt[String]('f', "fieldName") required () valueName "<name of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldName = x)
  } text "Name of info field in new vcf file"
  opt[String]('d', "fieldDescription") valueName "<description of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldDescription = x)
  } text "Description of field in new vcf file"
  opt[String]('t', "fieldType") valueName "<type of field in vcf file>" action {
    (x, c) =>
      c.copy(fieldType = x)
  } text "Type of field in new vcf file. Can be 'Integer', 'Flag', 'Character', 'Float'"
}
