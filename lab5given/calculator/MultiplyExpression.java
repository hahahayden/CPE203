class MultiplyExpression extends BinaryExpression
   implements Expression
{


   public MultiplyExpression(final Expression lft, final Expression rht)
   {
      super (lft,rht,"*");
   }

//   public String toString()
//   {
//      return "(" + lft + " * " + rht + ")";
//   }
//
//   public double evaluate(final Bindings bindings)
//   {
//      return lft.evaluate(bindings) * rht.evaluate(bindings);
//   }
protected double applyOperator(Double lft, Double rht) {
   return lft * rht;
}

}

