My work for [Advent of Code 2019](https://adventofcode.com/2019)

  - Written in Kotlin with TestNG for tests.
  - Uses [Codacy](https://www.codacy.com/) for code coverage reports.

# Codacy notes

I'm trying out Codacy for code coverage on this repo. See my [GitHub Action](/.github/workflows/push_master.yml) for how I've implemented uploads for that.
One gotcha I found is that the language needs to be specified for code coverage reports. 

```
 java -jar codacy-coverage-reporter-assembly.jar report -l Kotlin -r build/reports/jacoco/test/jacocoTestReport.xml
```

# Helpful resources

  - [IntCode Disassembler](https://janiczek.github.io/advent-of-code/Year2019/Intcode/Disasm/)
