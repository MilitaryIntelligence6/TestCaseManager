echo "npm run build!";
npm run build;
printf "run shell ./dist-cpy-script-dir.sh!\n";

#./dist-cpy-script-dir.sh;
git add ./dist/*

rm -r ../case-server/src/main/resources/web/dist/
cp -r ./dist ../case-server/src/main/resources/web/

git add ../case-server/src/main/resources/web/dist/*

