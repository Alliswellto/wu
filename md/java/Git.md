# Git

1. 一般开发流程

   ![image2022-7-13_16-30-14](../../Image\image2022-7-13_16-30-14.png)

   ![image2022-7-13_16-39-45](../../Image\image2022-7-13_16-39-45.png)

   

2.  Command

   git init 初始化仓库

   git remote 查看远程仓库

   git remote add origin [git@git.git](mailto:git@git.git) 将远程仓库 origin 与本地仓库进行关联

   git clone <reposicity> 将远程仓库代码克隆到本地

   git branch <branch_name> 创建分支

   git branch -r 查看远程分支

   git branch -a 查看本地分支

   git status 查看提交状态

   git add <file> 将文件提交到暂存区

   git commit -m 将文件从暂存区提交到本地仓库并附加注释

   git push 将本地仓库内容推送到远程仓库

   git push -u origin <branch_name> 将本地分支推送到远程

   git push origin <branch_name> 将暂存区文件推送到远程分支

   git diff 查看不同提交，工作区或分支之间的区别（不加参数为工作区与暂存区的区别，--cached 为暂存区与本地仓库的区别）

   git log 查看历史提交日志，会同步到远程仓库，只记录 commit 操作

   git reflog 查看所有日志，只存在于本地，没有远程日志

   git checkout <branch_name> 切换到对应分支

   git checkout HEAD^^^(HEAD~3) file 切换到三个版本前（也可以使用 git log 查看对应的版本号，然后使用 checkout 进行切换）

   git checkout -b <branch_name> <origin/<branch_name>> 根据远程对应分支在本地创建分支，并切换到对应分支

   git reset –mixed/hard/soft HEAD~ 重置版本（mixed 将重置版本的内容添加到暂存区，hard 将内容添加到暂存区与工作区，soft 将 HEAD 指针从当前版本指向重置版本，但不会将内容进行复制）

   git revert <version> 撤销一些提交（git reset –hard <version>也可以达到同样效果，但 reset 并不会把本次撤销操作当做一次新的提交，所以不推荐此方法重置提交）

   git fetch 将远程分支内容拉取到本地，但并不与本地分支合并

   git merge <origin/<branch_name>> 将远程分支拉取的内容合并到本地分支（当修改部分遇到冲突时，可以使用 abort 取消本次合并，或者定位到修改位置，删除不需要的部分重新 push）

   git pull 从远程仓库拉取最新内容到本地（fetch + merge）

   git check-pick <version> 将某次提交的内容合并到当前分支（若出现冲突，则在修改冲突内容后可以使用 –continue 参数继续提交，与 merge 相比，merge 会把所有修改都合并到当前分支）

   git rebase master 将对应分支合并到当前分支（merge是将两条岔路合并为一条路，而 rebase 是将当前分支合并到 master 分支之后，若 master 分支已包含部分变化内容，则跳过执行之后的合并）

   ![李艳武 > Git > image2022-7-13_18-30-40.png](https://wiki.corp.qunar.com/confluence/download/attachments/586922664/image2022-7-13_18-30-40.png?version=1&modificationDate=1657708241000&api=v2)

   ![李艳武 > Git > image2022-7-13_18-30-53.png](https://wiki.corp.qunar.com/confluence/download/attachments/586922664/image2022-7-13_18-30-53.png?version=1&modificationDate=1657708254000&api=v2)

3.  公钥配置

   `ssh-keygen -t rsa -C "email”`

   `Linux: `ssh-add ~/.ssh/id_rsa

   `Windows: `cat ~/.ssh/id_rsa.pub

4.  修改 git 配置信息

   git config -e 查看

   git config --global [user.name](http://user.name/) "yanwu.li" 

   git config --global user.email "yanwu.li@qunar.com" # 同上

   git config --global merge.tool "kdiff3" 要是没装KDiff3就不用设这一行

   git config --global push.default simple 要是你非要用低版本的Git（比如1.7.x），好吧，那就不设simple设current，否则你的Git不支持

   git config --global core.autocrlf false 让Git不要管Windows/Unix换行符转换的事

   git config --global gui.encoding utf-8 避免git gui中的中文乱码

   git config --global core.quotepath off 避免git status显示的中文文件名乱码

   `git config --global core.ignorecase false`

   Mac

   git config --global mergetool.kdiff3.path /Applications/kdiff3.app/Contents/MacOS/kdiff3

5. NOTE
   若本地与远程仓库有差异，push 不成功时，可以添加 force 参数，强制提交。