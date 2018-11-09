package com.SubjectService.SubjectService;

class Program {
  private String program_id;
  private String program_name;
  private String program_code;
  private String program_description;

  public Program() {

  }
  public Program(String program_id, String program_name, String program_code, String program_description) {
    this.program_id = program_id;
    this.program_name = program_name;
    this.program_code = program_code;
    this.program_description = program_description;
  }

  public String getProgram_id() {
    return program_id;
  }

  public void setProgram_id(String program_id) {
    this.program_id = program_id;
  }

  public String getProgram_name() {
    return program_name;
  }

  public void setProgram_name(String program_name) {
    this.program_name = program_name;
  }

  public String getProgram_code() {
    return program_code;
  }

  public void setProgram_code(String program_code) {
    this.program_code = program_code;
  }

  public String getProgram_description() {
    return program_description;
  }

  public void setProgram_description(String program_description) {
    this.program_description = program_description;
  }
}
