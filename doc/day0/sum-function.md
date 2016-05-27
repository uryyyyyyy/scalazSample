
sum関数を抽象化していく過程。

そのままで十分分かりやすい。

```
scala> def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
sum: [A](xs: List[A])(implicit m: Monoid[A])A

scala> implicit val intMonoid = IntMonoid
intMonoid: IntMonoid.type = IntMonoid$@3387dfac

scala> sum(List(1, 2, 3, 4))
res9: Int = 10
```

最終形はコレ。sum関数は、Monoidをmixinしてるどの型でも適用できるようになる。

sumと書いてはいるが、実装はmappendとmempty(mZero)によるので、こんなのもできる。

```
scala> val multiMonoid: Monoid[Int] = new Monoid[Int] {
         def mappend(a: Int, b: Int): Int = a * b
         def mzero: Int = 1
       }
multiMonoid: Monoid[Int] = $anon$1@48655fb6

scala> sum(List(1, 2, 3, 4))(multiMonoid)
res14: Int = 24
```

`def sum[A](xs: List[A], m: Monoid[A]): A`ってあるけど、sum[A]のAはいれなくてもいいの？推論してくれるの？