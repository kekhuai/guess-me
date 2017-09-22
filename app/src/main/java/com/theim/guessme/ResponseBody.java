package com.theim.guessme;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ResponseBody {

    private String jsonrpc;

    private int id;

    private Result result;

    public class Result {

        private int bitsUsed;

        private int bitsLeft;

        private int requestsLeft;

        private int advisoryDelay;

        private Random random;

        public class Random {

            private List<Integer> data;

            public List<Integer> getData() {
                return data;
            }

            public void setData(List<Integer> data) {
                this.data = data;
            }
        }

        public int getBitsUsed() {
            return bitsUsed;
        }

        public void setBitsUsed(int bitsUsed) {
            this.bitsUsed = bitsUsed;
        }

        public int getBitsLeft() {
            return bitsLeft;
        }

        public void setBitsLeft(int bitsLeft) {
            this.bitsLeft = bitsLeft;
        }

        public int getRequestsLeft() {
            return requestsLeft;
        }

        public void setRequestsLeft(int requestsLeft) {
            this.requestsLeft = requestsLeft;
        }

        public int getAdvisoryDelay() {
            return advisoryDelay;
        }

        public void setAdvisoryDelay(int advisoryDelay) {
            this.advisoryDelay = advisoryDelay;
        }

        public Random getRandom() {
            return random;
        }

        public void setRandom(Random random) {
            this.random = random;
        }
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
