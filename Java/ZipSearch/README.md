# Zip Search Challenge - Language Agnostic

Write a small CLI program that:

1. Store zip file foc-slack-export.zip locally.

1. Reads the zip file `foc-slack-export.zip` without unpacking it first.

1. Parses the JSON files found inside `foc-slack-export.zip`. Each JSON file contains exported Slack messages.

1. Scans each message for a given word (case-insensitive).

1. Supports the following flags:

   - `-file` : Path to a zip file containing exported Slack messages in JSON format. The zip _will_ contain folders!

   - `-word` : The word to search for (case-insensitive).

   - `-size` : Number of neighboring characters to show to the left and right of the matched word, i.e. to give the search result more meaningful context.

   - `-first` : Max number of search results to show.

   **NOTE: All flags are required!**

1. Is built and runs perfectly inside a Docker container.

   - You _must_ provide a `Dockerfile` that builds your program.

   - Your build _must_ be reproducable by us. We will run an automated test suite against your program which relies entirely on your `Dockerfile` to produce a working program.

   - We will build your Docker image using this command: `docker build -t zipsearch .`

   **NOTE: We've provided an example `Dockerfile` below!**

1. Produces output _exactly like_ in the examples shown below.

1. You can use _any_ of the following languages to implement your program:

   - Golang
   - JavaScript / TypeScript
   - Ruby
   - Python
   - Java
   - Scala
   - C#
   - C++
   - PHP
   - Rust

## Examples

```bash
# Example 1:
docker run --rm zipsearch -file foc-slack-export.zip -word niko -size 20 -first 3
#
# Your program should produce the EXACT output between BEGIN and END:
#
# BEGIN
# zip=foc-slack-export.zip
# file=introductions/2019-04-18.json ts=1555601957.036900 : ' of your projects.  Niko your point about ge'
# file=general/2018-08-31.json ts=1535741496.000100 : 'Hey Nikolas, I would love to'
# file=general/2017-10-03.json ts=1507066742.000051 : 'Hello everyone! //Niko is hopefully some s'
# END


# Example 2:
docker run --rm zipsearch -file foc-slack-export.zip -word CRAP -size 20 -first 4
#
# Your program should produce the EXACT output between BEGIN and END:
#
# BEGIN
# zip=foc-slack-export.zip
# file=introductions/2019-01-16.json ts=1547679117.090400 : ' date, I'll cut the crap and say hi: Hi :wav'
# file=introductions/2018-09-14.json ts=1536963014.000100 : 'astles. Because the crap you're building on '
# file=introductions/2018-09-14.json ts=1536975379.000100 : '&gt;Because the crap you're building on '
# file=meta/2019-05-25.json ts=1558783270.015700 : 'hange: even if it's crappy, let's use Slack''
# END

# Example 3:
docker run --rm zipsearch -file foc-slack-export.zip -word un-code -size 40 -first 1
#
# Your program should produce the EXACT output between BEGIN and END:
#
# BEGIN
# zip=foc-slack-export.zip
# file=random/2018-12-02.json ts=1543767323.008100 : 'Today I learned about site called "Un-code Mania" where "un" rhymes with "boon".  '
# END

# Example 4:
docker run --rm zipsearch -file foc-slack-export.zip -word "I'm not sure if" -size 15 -first 2
#
# Your program should produce the EXACT output between BEGIN and END:
#
# BEGIN
# zip=foc-slack-export.zip
# file=nyc/2018-06-20.json ts=1529530359.000208 : 'al friends, so I'm not sure if it's a public '
# file=meta/2018-12-21.json ts=1545403999.002100 : 'I'm not sure if it's worth it '
# END
```

**Note the following:**

- Result rows have the following format: `file={file path inside zip} ts={ts} : '{size chars left}{word}{size chars right}'`

- Any newlines found in the matching text `'{size chars left}{word}{size chars right}'` must be replaced by whitespace (' ')!

# Dockerfile example

```Dockerfile
FROM golang:1.14-alpine as builder

# Create a build dir.
WORKDIR /build

# Fetch dependencies.
COPY go.mod go.sum ./
RUN go mod download

# Copy code.
COPY cmd ./cmd
COPY pkg ./pkg

# Build our program inside the container.
RUN CGO_ENABLED=0 GOOS=linux go build -v -o /main ./cmd/zipsearch/main.go

# Back to root!
WORKDIR /

ENTRYPOINT ["/main"]
```

# Test suite

We've provided the `build-and-test.sh` to help you build and test your program.

Note that our own internal test suite _will contain additional tests_ and means that you can't optimize your program to only produce the outputs shown in the examples!

# How To Submit Your Code

1. Create a feature branch named `challenge` and submit your code as a PR.

   You can still make changes to your code after you've submitted your PR as long as it's before the stated deadline.

   Limits:

   - This challenge should take **max 3 hours** to complete.
   - You have **max 2 days** to submit your code counted from the time you receive our invitation to collaborate on Github.

# Grading & What To Expect

1. We will grade you on code clarity, naming, structure, program size and correctness.

   Our own internal reference solution is about 200 LOC (across 3 different Go packages) including comments and tests.

   Please try to keep your submission as short as possible, and remember to **stay true to the principles of KISS and YAGNI!**

1. **IMPORTANT: For this challange you will only be informed if you pass or not.
   We will NOT provide any additional feedback, so please do not ask for it**.
