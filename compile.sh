#!/bin/bash

java -cp .:antlr.jar org.antlr.v4.Tool IsiLang.g4 -package br.com.professorisidro.Isilanguage.parser -o ./src/br/com/professorisidro/isilanguage/parser/
