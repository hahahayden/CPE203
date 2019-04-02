class  SubtractExpression extends BinaryExpression
   implements Expression
{

   protected SubtractExpression(final Expression lft, final Expression rht)
   {
     super(lft,rht,"-");
   }

protected double applyOperator(Double lft, Double rht) {
        return lft - rht;
        }
}

