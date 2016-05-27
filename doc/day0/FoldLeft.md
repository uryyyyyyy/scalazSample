
FoldLeftの定義が、

`def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B`

になってるけど、この例ではAとBが同じ型。

`FoldLeft[F[_]]` って記述が違和感あるけど、`new FoldLeft[List] ` するために必要な記述みたい。

newの段階では`List[A]`のAの方は決まってないけど、抽象型は入れられないのでこういう形になってる？




> 上の例における trait の Monoid と FoldLeft は Haskell の型クラスに相当する。

`implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List]`

この実装は、mixinとはちょっと違うのかな？

ある型がtraitの特性を持った（mixin）わけではなくて、
その型クラスに含まれる型を新たに定義したということだよね？