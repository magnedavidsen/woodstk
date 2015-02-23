var employees = require('./test-employees.json');
var statistics = require('./../statistics.js');

describe("Gender distribution is calculated correctly", function() {

  statistics.setEmployees(employees);

  var genderDistribution = statistics.calculateGenderDistribution();

  it("counts the number of men", function() {
    expect(genderDistribution.numberOfMen).toBe(14);
  });

  it("counts the number of women", function() {
    expect(genderDistribution.numberOfWomen).toBe(5);
  });

  it("calculates the percentage of men", function() {
    expect(genderDistribution.percentageOfMen).toBe(74);
  });

  it("calculates the percentage of men", function() {
    expect(genderDistribution.percentageOfWomen).toBe(26);
  });
});
