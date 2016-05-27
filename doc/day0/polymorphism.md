
http://eed3si9n.com/learning-scalaz/ja/polymorphism.html

## パラメータ多相

型クラスと同じ？

多相（Polymorphism）には複数の種類があるみたい。

どれも、「型をグループ化し、それらに共通の性質をもたせる。」ことを言ってるっぽい。

* 型クラス(Type class)
    - Haskellで初めに使われた概念。アドホック多相の実装の一種？
* パラメータ多相（parameter Polymorphism）
    - ある値の型に型変数を与える。Scalaのtraitのmixinみたいな？
* アドホック多相（ad hoc Polymorphism）
    - オーバーロードとも呼ばれる。Javaの多相化と同じ？

http://jutememo.blogspot.jp/2009/05/haskell.html

### 例

```
scala> def plus[A](a1: A, a2: A): A = ???
plus: [A](a1: A, a2: A)A
```

たとえば、上記の定義のplusという関数をもつ型らをグループ化するときに、

```
scala> trait Plus[A] {
         def plus(a1: A, a2: A): A
       }
defined trait Plus

scala> def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
plus: [A](a1: A, a2: A)(implicit evidence$1: Plus[A])A
```

こんなかんじのことをすれば、Plusをmixinした型（暗黙を含む）に置いては`plus(a1: A, a2: A): A`を持つはずなので、
`def plus[A: Plus](a1: A, a2: A): A`を呼ぶだけでAの型によって挙動を変えることができる。
