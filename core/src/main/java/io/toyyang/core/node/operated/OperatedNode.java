package io.toyyang.core.node.operated;

import io.toyyang.core.node.Node;
import io.toyyang.core.node.Operator;

public class OperatedNode extends Node {
    private Operator operator;

    private OriginNode origin;

    private OperatedNode(boolean isNumber, Kind kind) {
        super(isNumber, kind);
    }

    public OperatedNode(boolean isNumber) {
        this(isNumber, Kind.ARGUMENT);
    }

    public OriginNode getOrigin() {
        return origin;
    }

    public void setOrigin(OriginNode origin) {
        this.origin = origin;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public static final class Builder {
        protected String rawValue;
        private Operator operator;
        private OriginNode origin;
        private boolean isNumber;
        private Kind kind;

        private Builder() {
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder withOperator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public Builder withOrigin(OriginNode origin) {
            this.origin = origin;
            return this;
        }

        public Builder withRawValue(String rawValue) {
            this.rawValue = rawValue;
            return this;
        }

        public Builder withIsNumber(boolean isNumber) {
            this.isNumber = isNumber;
            return this;
        }

        public Builder withKind(Kind kind) {
            this.kind = kind;
            return this;
        }

        public OperatedNode build() {
            OperatedNode operatedNode = new OperatedNode(isNumber, kind);
            operatedNode.setOperator(operator);
            operatedNode.setOrigin(origin);
            operatedNode.setRawValue(rawValue);
            return operatedNode;
        }
    }
}
