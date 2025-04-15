# Timetable Generator  

## Overview  
This program generates school timetables based on given inputs such as teacher availability, subject details, and school configuration.  

- Input: JSON files with school details  
- Processing: Algorithms to generate an optimized timetable  
- Output: JSON timetable format  

## Project Structure
com.school.timetable  
├── application                # Handles external interactions (e.g., REST API)  
│   ├── RestAPIAdapter.java    # API adapter  
│  
├── domain                     # Core business logic  
│   ├── algorithm              # Contains scheduling algorithms  
│   │   ├── model              # Models used within algorithms  
│   │   ├── utils              # Utility functions for algorithm logic  
│   │  
│   ├── datastructure          # Custom data structures for timetable management  
│   │   ├── model              # Models used in data structures  
│   │  
│   ├── model                  # Main domain models  
│   │   ├── common             # Shared/common models (e.g., DayOfWeek)  
│   │   ├── input              # Models for JSON input  
│   │   ├── output             # Models for JSON output  
│   │   ├── parser             # JSON parsing logic  
│   │  
│   ├── populator              # Converts input JSON into internal data structures  
│  
├── test                       # Unit tests  
│   ├── domain.algorithm.utils # Tests for algorithm logic  
│   ├── domain.datastructure   # Tests for data structures  
│   ├── domain.model.parser    # Tests for JSON parsing  
│   ├── domain.populator       # Tests for populators  
│   ├── resources/             # Test JSON files
