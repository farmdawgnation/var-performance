# Var Performance Testing

This repository does some performance testing of RequestVars, TransientRequestVars, and SessionVars.
The purpose of these tests is to investigate and document the performance characteristics of these
Vars. This is a follow up to the [FactoryMaker performance testing](https://github.com/farmdawgnation/factorymaker-performance)
to try and determine whether or not Vars themselves could be responsible for any (or all of)
the delay in computing the value of a FactoryMaker.

## Test Method

This test method varies slightly from the method I took in the first performance test. In this
test, we create one of a SessionVar, RequestVar, and TransientRequestVar. We then do ten test
passes, where one test pass consists of reading the Var in question a million times, and then
average the results of those tests.

## Results

I ran the above sequence four times and got the following results:

```
Results:
Request var test: 43
Transient request var test: 65
Session var test: 222

Results:
Request var test: 31
Transient request var test: 40
Session var test: 223

Results:
Request var test: 32
Transient request var test: 47
Session var test: 217

Results:
Request var test: 40
Transient request var test: 44
Session var test: 242
```

The RequestVar and TransientRequestVar appear to perform similarly, with TransientRequestVar being
a bit slower. SessionVar is in a completely different order of magnitude, meaning it is comparitively
much more expensive to compute.

## Analysis

To dig deeper and know for sure why these numbers are what they are I'd need a YourKit license, which
I don't have. But I can speculate a bit. For reading SessionVars we do a bit of synchronization to
pull that value out in a thread-safe manner. My money is that this is paying the cost of that
synchronization.
