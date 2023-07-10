#!/usr/bin/env python3

import subprocess
import sys
import re

# Get the actual commit message of the last commit
commit_msg = subprocess.check_output(['git', 'log', '--format=%B', '-n', '1']).decode().strip()

print("Checking commit message: ")
print(commit_msg)

# Rule 1: Separate subject from body with a blank line
if not re.match(r'^\s+.*\n\n', commit_msg, re.MULTILINE):
    print("Invalid commit message format! Please separate subject from body with a blank line.")
    sys.exit(1)

# Rule 2: Limit the subject line to 50 characters
subject_line = commit_msg.split('\n', 1)[0]
if len(subject_line) > 50:
    print("Invalid commit message format! Subject line should be limited to 50 characters.")
    sys.exit(1)

# Rule 3: Capitalize the subject line
if not re.match(r'^(?:\[.*\]|[\s\S]*[A-Z].*)$', subject_line):
    print("Invalid commit message format! Subject line should start with a capital letter or square brackets.")
    sys.exit(1)

# Rule 4: Do not end the subject line with a period
if subject_line.endswith('.'):
    print("Invalid commit message format! Subject line should not end with a period.")
    sys.exit(1)

# Rule 5: Use the imperative mood in the subject line
imperative_keywords = ['Add', 'Fix', 'Update', 'Remove', 'Refactor', 'Implement', 'Merge', 'Revert']
if not any(keyword in subject_line.split() for keyword in imperative_keywords):
    print("Invalid commit message format! Subject line should use the imperative mood.")
    sys.exit(1)

# Rule 6: Wrap the body at 72 characters
body_lines = commit_msg.split('\n')[2:]
for line in body_lines:
    if len(line) > 72:
        print("Invalid commit message format! Body lines should be wrapped at 72 characters.")
        sys.exit(1)

print("Commit message is valid.")
sys.exit(0)