package org.gucardev.chain_of_resp_validation_example;

public abstract class Handler<T> {

  private Handler<T> next;

  public Handler<T> setNextHandler(Handler<T> next) {
    if (this.next == null) {
      this.next = next;
    } else {
      this.next.setNextHandler(next);
    }
    return this;
  }

  public abstract boolean handle(T payload);

  protected boolean handleNext(T payload) {
    if (next == null) {
      return true;
    }
    return next.handle(payload);
  }
}