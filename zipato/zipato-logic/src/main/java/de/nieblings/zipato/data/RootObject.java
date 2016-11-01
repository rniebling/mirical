package de.nieblings.zipato.data;

import java.util.ArrayList;

public class RootObject
{
  private Instance response;
  private boolean retry;
  private String ref;
  private ArrayList<String> errors;
  private boolean success;
  private String error;

  public Instance getResponse() { return this.response; }

  public void setResponse(Instance response) { this.response = response; }


  public boolean getRetry() { return this.retry; }

  public void setRetry(boolean retry) { this.retry = retry; }


  public String getRef() { return this.ref; }

  public void setRef(String ref) { this.ref = ref; }


  public ArrayList<String> getErrors() { return this.errors; }

  public void setErrors(ArrayList<String> errors) { this.errors = errors; }


  public boolean getSuccess() { return this.success; }

  public void setSuccess(boolean success) { this.success = success; }

  public String getError() { return this.error; }

  public void setError(String error) { this.error = error; }
}
