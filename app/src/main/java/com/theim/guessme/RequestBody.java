package com.theim.guessme;

public class RequestBody {

    private String jsonrpc;

    private String method;

    private int id;

    private Params params;

    class Params {

        private String apiKey;

        private int n;

        private int min;

        private int max;

        private boolean replacement;

        private int base;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public boolean isReplacement() {
            return replacement;
        }

        public void setReplacement(boolean replacement) {
            this.replacement = replacement;
        }

        public int getBase() {
            return base;
        }

        public void setBase(int base) {
            this.base = base;
        }
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestBody that = (RequestBody) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
