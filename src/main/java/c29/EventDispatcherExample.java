package c29;

public class EventDispatcherExample {

    static class InputEvent extends Event {
        private final int x;
        private final int y;

        public InputEvent(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         *  get x
         */
        public int getX() {
            return x;
        }

        /**
         * get y
         */
        public int getY() {
            return y;
        }

    }

    static class ResultEvent extends Event {

        private final int result;

        /**
         * constructor
         */
        public ResultEvent(int result) {
            this.result = result;
        }

        // get result
        public int getResult() {
            return result;
        }


    }
    static class ResultEventHandler implements Channel<ResultEvent>{

        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("Result: " + message.getResult());
        }
    }

    static class InputEventHander implements Channel<InputEvent> {
        private final EventDispatcher dispatcher;

        /**
         * constructor
         */
        public InputEventHander(EventDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        public void dispatch(InputEvent message) {
            System.out.println("Input: " + message.getX() + " " + message.getY());

            int result = message.getX() + message.getY();

            dispatcher.dispatch(new ResultEvent(result));
        }
    }

    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerChannel(InputEvent.class, new InputEventHander(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());

        dispatcher.dispatch(new InputEvent(1, 2));
    }

}
