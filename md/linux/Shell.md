# Shell

[Linux 命令大全](https://www.runoob.com/linux/linux-command-manual.html)

# 1. awk

>awk 命令也是逐行扫描文件（从第 1 行到最后一行），寻找含有目标文本的行，如果匹配成功，则会在该行上执行用户想要的操作；反之，则不对行做任何处理。

## 1.1 基本用法

```shell
awk action filename
awk '{print $0}' a.txt

-F fs	指定以 fs 作为输入行的分隔符，awk 命令默认分隔符为空格或制表符。
-f file	从脚本文件中读取 awk 脚本指令，以取代直接在命令行中输入指令。
-v var=val	在执行处理过程之前，设置一个变量 var，并给其设备初始值为 val。
```

## 1.2 变量

除了`$ + 数字`表示某个字段，`awk`还提供其他一些变量。

变量`NF`表示当前行有多少个字段，因此`$NF`就代表最后一个字段。

```sh
echo 'this is a test' | awk '{print $NF}'
test

# NF - 1 为倒数第二个字段
# print 命令里面的逗号，表示输出的时候，两个部分之间使用空格分隔
awk -F ':' '{print $1, $(NF-1)}' demo.txt
root /root
daemon /usr/sbin
bin /bin
sys /dev
sync /bin

# 变量NR表示当前处理的是第几行
# print命令里面，如果原样输出字符，要放在双引号里面
awk -F ':' '{print NR ") " $1}' demo.txt
1) root
2) daemon
3) bin
4) sys
5) sync

# 修改对应字段的值
echo "My name is Rich" | awk '{$4="Christine"; print $0}'
My name is Christine
```

## 1.3 函数

内置函数手册：[Built-in (The GNU Awk User’s Guide)](https://www.gnu.org/software/gawk/manual/html_node/Built_002din.html#Built_002din)

```sh
awk -F ':' '{ print toupper($1) }' demo.txt
ROOT
DAEMON
BIN
SYS
SYNC

# tolower()：字符转为小写。
# length()：返回字符串长度。
# substr()：返回子字符串。
# sin()：正弦。
# cos()：余弦。
# sqrt()：平方根。
# rand()：随机数。
```

## 1.4 条件

`awk`允许指定输出条件，只输出符合条件的行。

输出条件要写在动作的前面。

```sh
awk '条件 动作' 文件名

# print命令前面是一个正则表达式，只输出包含usr的行
awk -F ':' '/usr/ {print $1}' demo.txt
root
daemon
bin
sys

# 输出奇数行
awk -F ':' 'NR % 2 == 1 {print $1}' demo.txt
root
bin
sync

# 输出第三行以后的行
awk -F ':' 'NR >3 {print $1}' demo.txt
sys
sync

# 输出第一个字段等于指定值的行
awk -F ':' '$1 == "root" {print $1}' demo.txt
root

awk -F ':' '$1 == "root" || $1 == "bin" {print $1}' demo.txt
root
bin
```

## 1.5 if

`awk`提供了`if`结构，用于编写复杂的条件。

```bash
# 输出第一个字段的第一个字符大于m的行
awk -F ':' '{if ($1 > "m") print $1}' demo.txt
root
sys
sync

# 还可指定 else
awk -F ':' '{if ($1 > "m") print $1; else print "---"}' demo.txt
root
---
---
sys
sync
```

## 1.6 从文件读取程序

```sh
cat awk.sh
{print $1 "'s home directory is " $6}
awk -F: -f awk.sh /etc/passwd
root's home directory is /root
bin's home directory is /bin
```

## 1.7 BEGIN

awk 中还可以指定脚本命令的运行时机。默认情况下，awk 会从输入中读取一行文本，然后针对该行的数据执行程序脚本，但有时可能需要在处理数据前运行一些脚本命令，这就需要使用 BEGIN 关键字。

```sh
awk 'BEGIN {print "The data3 File Contents:"}
> {print $0}' data3.txt
The data3 File Contents:
Line 1
Line 2
Line 3
```

## 1.8 END

和 BEGIN 关键字相对应，END 关键字允许我们指定一些脚本命令，awk 会在读完数据后执行它们。

```sh
awk 'BEGIN {print "The data3 File Contents:"}
> {print $0}
> END {print "End of File"}' data3.txt
The data3 File Contents:
Line 1
Line 2
Line 3
End of File
```

# 2. sed

[sed](http://c.biancheng.net/linux/sed.html)

[sed](http://c.biancheng.net/view/4028.html)

## 2.1 概述

>sed 是 stream editor 的缩写，中文称之为“流编辑器”。
>
>sed 命令是一个面向行处理的工具，它以“行”为处理单位，针对每一行进行处理，处理后的结果会输出到标准输出（STDOUT）。
>
>处理时，sed 会把要处理的行存储在缓冲区中，接着用 sed 命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕。接着处理下一行，这样不断重复，直到文件末尾。这个缓冲区被称为“模式空间”（pattern space）。

## 2.2 参数

```sh
# 格式
sed [-hnV][-e<script>][-f<script文件>][文本文件]
多个脚本命令 {
	script 1
	script 2
	...
}

# 参数
-e<script>或--expression=<script> 以选项中指定的script来处理输入的文本文件。
-f<script文件>或--file=<script文件> 以选项中指定的script文件来处理输入的文本文件。
-h或--help 显示帮助。
-n或--quiet或--silent 仅显示script处理后的结果。
-V或--version 显示版本信息。
-i 直接修改文件

# 动作
a ：新增， a 的后面可以接字串，而这些字串会在新的一行出现(目前的下一行)～
c ：取代， c 的后面可以接字串，这些字串可以取代 n1,n2 之间的行！
d ：删除，因为是删除啊，所以 d 后面通常不接任何东东；
i ：插入， i 的后面可以接字串，而这些字串会在新的一行出现(目前的上一行)；
p ：打印，亦即将某个选择的数据印出。通常 p 会与参数 sed -n 一起运行～
s ：取代，可以直接进行取代的工作哩！通常这个 s 的动作可以搭配正规表示法！例如 1,20s/old/new/g 就是啦！
y：转换，一比一，若长度不同报错
w: 输出到文件
```

## 2.3 用法

```sh
cat testfile #查看testfile 中的内容  
HELLO LINUX!  
Linux is a free unix-type opterating system.  
This is a linux testfile!  
Linux test 
Google
Taobao
Runoob
Tesetfile
Wiki

# -e 执行后面的 script 
# a 在之后添加内容
# i 在之前添加内容
# 在第四行后添加 newLine
# sed -e '4a newLine' testfile
# sed -e '4i newLine' testfile
# 若要添加多行,则
# 每一行之间都必须要以反斜杠 \ 来进行新行标记。
sed '4a new \
new2' testfile
sed -e 4a\newLine testfile 
HELLO LINUX!  
Linux is a free unix-type opterating system.  
This is a linux testfile!  
Linux test 
newLine
Google
Taobao
Runoob
Tesetfile
Wiki

# d 删除 3-最后一行
# 3,5 3-5 行
# 2d 2行
nl testfile | sed '3,$d' 
     1  HELLO LINUX!  
     2  Linux is a free unix-type opterating system.  
     
# c 替代
nl testfile | sed '2,5c No 2-5 number'
     1  HELLO LINUX!  
No 2-5 number
     6  Taobao
     7  Runoob
     8  Tesetfile
     9  Wiki
# p 打印
# 一般配合 -n 使用
nl c.txt | sed -n '2,5p'
     2  Linux is a free unix-type opterating system.
     3  This is a linux testfile!
     4  Linux test
     5  Google
# 匹配对应字符
nl c.txt | sed -n '/oo/p'
     5  Google
     7  Runoob
     
# 切除 ip 之前的所有字符
ifconfig ens33 | grep inet | awk 'NR == 1 {print $0}' | sed -e 's/^.*inet//g'
 192.168.231.128  netmask 255.255.255.0  broadcast 192.168.231.255
 
# y 替换
 # 将 1 替换为 7
 sed -e 'y/123/789'
This is line number 1 -> 7.
This is line number 2 -> 8.
This is line number 3 -> 9.
 
# w 将结果输出到文件
sed '1,2w d.txt' c.txt
```

`NOTE:`nl 为文件内容添加行号输出

# 3. sort

>Linux sort 命令用于将文本文件内容加以排序。
>
>sort 可针对文本文件的内容，以行为单位来排序。

```sh
# 格式
sort [-bcdfimMnr][-o<输出文件>][-t<分隔字符>][+<起始栏位>-<结束栏位>][--help][--verison][文件][-k field1[,field2]]

-b 忽略每行前面开始出的空格字符。
-c 检查文件是否已经按照顺序排序。
-d 排序时，处理英文字母、数字及空格字符外，忽略其他的字符。
-f 排序时，将小写字母视为大写字母。
-i 排序时，除了040至176之间的ASCII字符外，忽略其他的字符。
-m 将几个排序好的文件进行合并。
-M 将前面3个字母依照月份的缩写进行排序。
-n 依照数值的大小排序。
# -u 意味着是唯一的(unique)，输出的结果是去完重了的。
# -o<输出文件> 将排序后的结果存入指定的文件。
# -r 以相反的顺序来排序。
# -t<分隔字符> 指定排序时所用的栏位分隔字符。
+<起始栏位>-<结束栏位> 以指定的栏位来排序，范围由起始栏位到结束栏位的前一栏位。
--help 显示帮助。
--version 显示版本信息。
# [-k field1[,field2]] 按指定的列进行排序。
```

# 4. grep

```sh
c*	将匹配 0 个（即空白）或多个字符 c（c 为任一字符）。
.	将匹配任何一个字符，且只能是一个字符。
[xyz]	匹配方括号中的任意一个字符。
[^xyz]	匹配除方括号中字符外的所有字符。
^	锁定行的开头。
$	锁定行的结尾。

# 在基本正则表达式中，如通配符 *、+、{、|、( 和 )等，已经失去了它们原本的含义，而若要恢复它们原本的含义，则要在之前添加反斜杠 \，如 \*、\+、\{、\|、\( 和 \)。
```

```shell
-c # 仅列出文件中包含模式的行数
grep -c a a.cpp
-i # 忽略模式中的字母大小写
grep -i a a.cpp
-l # 列出带有匹配行的文件名
grep -l a a.cpp b.cpp
-L # 列出不带有匹配行的文件名
grep -L limit a.cpp b.cpp
grep -L iostream a.cpp b.cpp
-v # 列出没有匹配模式的行
-w # 将表达式当做一个完整的单字符进行搜寻，忽略那些部分匹配的行
# 什么都匹配不到
grep -w ^#  a.cpp
# 可以匹配到所有头文件
grep -w ^#include  a.cpp
```

# ... 正则表达式

[正则表达式30分钟入门教程 (deerchao.cn)](https://deerchao.cn/tutorials/regex/regex.htm)