#!/bin/bash

read -p "Enter database file path: " DB_PATH

if [ ! -d $DB_PATH ]; then
   mkdir $DB_PATH
else
   read -p "File already exists. Remove? [Y/n]: " REM
   if [[ $REM="Y" || $REM="y" ]]; then
        rm -rf $DB_PATH
        mkdir $DB_PATH
   fi
fi
