package org.gucardev.example1;

public abstract class Handler<T> {

  private Handler next;

  public Handler setNextHandler(Handler next) {
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
