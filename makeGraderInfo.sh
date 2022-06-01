#!/bin/bash
cwd="$pwd"
echo -e "{"
echo -e "\t\"name\": \"AuD-2022-H$1\","
echo -e "\t\"assignmentIds\": [\"h$1\"],"
echo -e "\t\"sourceSets\": ["
echo -e "\t\t{"
echo -e "\t\t\t\"name\": \"grader\","
echo -e "\t\t\t\"files\": ["
cd src/grader/java
first=true
for f in $(find . -iname "*.java" | sed 's|^./||');do
    if [ $first = true ]; then
        printf ",\n"
    fi
    printf "\t\t\t\t\"%s\"" "$f"
done
printf "\n"
echo -e "\t\t\t]"
echo -e "\t\t},"
echo -e "\t\t{"
echo -e "\t\t\t\"name\": \"solution\","
echo -e "\t\t\t \"files\": ["
cd ../../main/java
first=true
for f in $(find . -iname "*.java" | sed 's|^./||');do
    if [ $first = true ]; then
        printf ",\n"
    fi
    printf "\t\t\t\t\"%s\"" "$f"
done
printf "\n"
echo -e "\t\t\t]"
echo -e "\t\t}"
echo -e "\t]"
echo -e "}"
cd "../../.."
