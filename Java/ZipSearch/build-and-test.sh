#!/bin/bash

echo "Building .."
docker build -t zipsearch . > /dev/null

echo "Running tests .."

TEST1R=`docker run --rm zipsearch -file foc-slack-export.zip -word niko -size 20 -first 3`

TEST1="zip=foc-slack-export.zip
file=introductions/2019-04-18.json ts=1555601957.036900 : ' of your projects.  Niko your point about ge'
file=general/2018-08-31.json ts=1535741496.000100 : 'Hey Nikolas, I would love to'
file=general/2017-10-03.json ts=1507066742.000051 : 'Hello everyone! //Niko is hopefully some s'"

if [ "$TEST1" = "$TEST1R" ]; then
    echo "TEST1 PASS"
else
    echo "TEST1 FAILED!"
    echo "TEST1 EXPECTED"
    echo "=============="
    echo "$TEST1"
    echo "TEST1 ACTUAL"
    echo "============"
    echo "$TEST1R"
fi

TEST2R=`docker run --rm zipsearch -file foc-slack-export.zip -word CRAP -size 20 -first 4`

TEST2="zip=foc-slack-export.zip
file=introductions/2019-01-16.json ts=1547679117.090400 : ' date, I'll cut the crap and say hi: Hi :wav'
file=introductions/2018-09-14.json ts=1536963014.000100 : 'astles. Because the crap you're building on '
file=introductions/2018-09-14.json ts=1536975379.000100 : '&gt;Because the crap you're building on '
file=meta/2019-05-25.json ts=1558783270.015700 : 'hange: even if it's crappy, let's use Slack''"

if [ "$TEST2" = "$TEST2R" ]; then
    echo "TEST2 PASS"
else
    echo "TEST2 FAILED!"
    echo "TEST2 EXPECTED"
    echo "=============="
    echo "$TEST2"
    echo "TEST2 ACTUAL"
    echo "============"
    echo "$TEST2R"
fi

TEST3R=`docker run --rm zipsearch -file foc-slack-export.zip -word un-code -size 40 -first 1`

TEST3="zip=foc-slack-export.zip
file=random/2018-12-02.json ts=1543767323.008100 : 'Today I learned about site called \"Un-code Mania\" where \"un\" rhymes with \"boon\".  '"

if [ "$TEST3" = "$TEST3R" ]; then
    echo "TEST3 PASS"
else
    echo "TEST3 FAILED!"
    echo "TEST3 EXPECTED"
    echo "=============="
    echo "$TEST3"
    echo "TEST3 ACTUAL"
    echo "============"
    echo "$TEST3R"
fi

TEST4R=`docker run --rm zipsearch -file foc-slack-export.zip -word "I'm not sure if" -size 15 -first 2`

TEST4="zip=foc-slack-export.zip
file=nyc/2018-06-20.json ts=1529530359.000208 : 'al friends, so I'm not sure if it's a public '
file=meta/2018-12-21.json ts=1545403999.002100 : 'I'm not sure if it's worth it '"

if [ "$TEST4" = "$TEST4R" ]; then
    echo "TEST4 PASS"
else
    echo "TEST4 FAILED!"
    echo "TEST4 EXPECTED"
    echo "=============="
    echo "$TEST4"
    echo "TEST4 ACTUAL"
    echo "============"
    echo "$TEST4R"
fi
