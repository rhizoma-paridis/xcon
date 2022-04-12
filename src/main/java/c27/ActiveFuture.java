package c27;

import c19.FutureTask;

public class ActiveFuture<T> extends FutureTask<T>{

    @Override
    protected void finish(T result) {
        super.finish(result);
    }

}
