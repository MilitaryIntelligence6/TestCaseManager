git add ./dist/*

rm -r ../case-server/src/main/resources/web/dist/
cp -r ./dist ../case-server/src/main/resources/web/

git add ../case-server/src/main/resources/web/dist/*
