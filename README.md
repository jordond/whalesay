<h1 align="center" style="border-bottom: none;">WhaleSay 🐳</h1>
<h3 align="center">A English to Whale translator!</h3>
<p align="center">
  <a href="https://circleci.com/gh/worldturtlemedia/whalesay">
    <img alt="CircleCI All" src="https://img.shields.io/circleci/project/github/worldturtlemedia/whalesay.svg">
  </a>
  <a href="https://circleci.com/gh/worldturtlemedia/whalesay/tree/master">
    <img alt="CircleCI Stable" src="https://img.shields.io/circleci/project/github/worldturtlemedia/whalesay/master.svg">
  </a>
  <a href="https://circleci.com/gh/worldturtlemedia/whalesay/tree/develop">
    <img alt="CircleCI Develop" src="https://img.shields.io/circleci/project/github/worldturtlemedia/whalesay/develop.svg">
  </a>
</p>
<p align="center">
  <a href="https://waffle.io/worldturtlemedia/whalesay">
    <img alt="Waffle.io" src="https://badge.waffle.io/worldturtlemedia/whalesay.svg?columns=all">
  </a>
</p>

**WhaleSay** is a app for translating English text or speech into the Whale language "Whalese".  Visit [whalesay](https://whalesay.ca) for more information!

## Highlights

- Android application
  - Soon to be on the Google Play store!
- Beautiful
- Translates text or speech
  - Speech requires the Microphone permission
- FREE & NO ads!

This project is inspired by [Talk To Whales](https://talktowhales.com/). Thanks to [Arthur Sousa](http://arthursousa.com/) for his inspiration.

## Permissions

- Required:
  - Internet: Used to talk to our science servers to translate your English into Whalese.
  - Access Network State: Used to determine if you have internet. (Internet is required to translate!)
- Optional:
  - Microphone: Required _if_ you wan't to use the Speech to Text feature, you can opt-out and use the Text only functionality.

## Installation

WhaleSay is not yet available on the Play store yet, as it is not complete.  You can however download the latest `.apk` from [CircleCI](https://circleci.com/gh/worldturtlemedia/whalesay)

## Contributing

All contributions are welcome!

This repository uses [ktlint](https://github.com/shyiko/ktlint) git hook, so every time you `git commit` it will run to make sure your code fits the style guide.

I recommend running `./gradlew ktlintFormat` before commiting so it can auto-fix some common problems.

## Note

Our "scientists" are actually frauds and don't exist.  WhaleSay actually uses Googles Speech-to-Text and Text-to-Speech Cloud API's to generate the "Whalese".  Shhh don't tell anyone ;)

## License

```text
MIT License

Copyright (c) 2019 Jordon de Hoog

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
