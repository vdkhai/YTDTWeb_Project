@echo off

rem ----- Cac dong REM la de dung duong dan truc tiep ------
rem if exist "C:\Users\ies\AppData\LocalLow\Google\Google Gears for Internet Explorer\localhost\http_8088\database-Quan_Ly_Y_Te#database" (

if exist %1 (
goto :_copy
) else (
goto :eof
)

:_copy

rem net use s: \\10.0.0.12\ShareFiles\BAO.TTC /user:10.0.0.12\bao-ttc 50100152
net use s: %2 %3 %4
rem xcopy "C:\Users\ies\AppData\LocalLow\Google\Google Gears for Internet Explorer\localhost\http_8088\database-Quan_Ly_Y_Te#database" t:\ /y
xcopy %1 s:\ /y

rem exit

rem autocopy.bat "C:\Users\ies\AppData\LocalLow\Google\Google Gears for Internet Explorer\localhost\http_8088\database-Quan_Ly_Y_Te#database" "\\10.0.0.12\ShareFiles\BAO.TTC" "/user:10.0.0.12\bao-ttc 50100152"
