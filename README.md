Shopping Cart [![Build Status](https://travis-ci.org/lishan/Git0527.svg?branch=master)](https://travis-ci.org/lishan/Git0527.svg?branch=master)
---

1.	discount
2.	preferential treatment

TODO
---
1. travis ci
2. editor config
3. maven plugins

TestCase 1
---
2013.11.11 | 0.7 | 电子 //促销信息,格式为:日期 | 折扣 | 产品品类,可有多个,每个一行,如果没有则保留一个空行
//空行分隔
1 * ipad : 2399.00
1 * 显示器: 1799.00 //所购产品,每种一行,格式为:数量 * 商品 : 单价
12 * 啤酒: 25.00
5 * 面包: 9.00
//空行分隔
2013.11.11 //结算日期
2014.3.2 1000 200 //优惠券信息,示例为 2014 年 3月2日到期,满 1000返 200,空格分隔,如果没有则保留一个空行

TestCase 2
---
//空行
3 * 蔬菜: 5.98
8 * 餐巾纸: 3.20
//空行
2014.01.01
//空行

