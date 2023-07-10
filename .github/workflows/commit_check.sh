#!/bin/bash

# Rule 1: Separate subject from body with a blank line
if [[ ! $COMMIT_MSG =~ ^[[:space:]].+ ]]; then
  echo "Invalid commit message format! Please separate subject from body with a blank line."
  exit 1
fi

# Rule 2: Limit the subject line to 50 characters
SUBJECT_LINE=$(echo "$COMMIT_MSG" | head -n 1)
if [[ ${#SUBJECT_LINE} -gt 50 ]]; then
  echo "Invalid commit message format! Subject line should be limited to 50 characters."
  exit 1
fi

# Rule 3: Capitalize the subject line
if [[ ! $SUBJECT_LINE =~ ^(\[.*\]|[A-Z].*)$ ]]; then
  echo "Invalid commit message format! Subject line should start with a capital letter or square brackets."
  exit 1
fi

# Rule 4: Do not end the subject line with a period
if [[ $SUBJECT_LINE =~ \.$ ]]; then
  echo "Invalid commit message format! Subject line should not end with a period."
  exit 1
fi

# Rule 5: Use the imperative mood in the subject line
if [[ ! $SUBJECT_LINE =~ ^(Add|Fix|Update|Remove|Refactor|Implement|Merge|Revert) ]]; then
  echo "Invalid commit message format! Subject line should use the imperative mood."
  exit 1
fi

# Rule 6: Wrap the body at 72 characters
BODY=$(echo "$COMMIT_MSG" | tail -n +3)
while IFS= read -r line; do
  if [[ ${#line} -gt 72 ]]; then
    echo "Invalid commit message format! Body lines should be wrapped at 72 characters."
    exit 1
  fi
done <<< "$BODY"

echo "Commit message is valid."
exit 0
