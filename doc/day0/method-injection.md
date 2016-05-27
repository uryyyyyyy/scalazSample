
最初に言っていたように、implicitで割りこませることで、
IntやStringへメソッドを追加（したかのように）できる。

もちろん、この例の場合はMonoidの具象クラス（IntMonoidなど）を持っている必要があるはず。
（言い換えれば、mappendとmzeroを持つMonoid[A]のインスタンスかな。）

