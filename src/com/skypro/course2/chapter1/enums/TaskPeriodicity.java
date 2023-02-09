package com.skypro.course2.chapter1.enums;

import com.skypro.course2.chapter1.task.*;

public enum TaskPeriodicity {
    ONETIME {
        @Override
        public Task createTask() {
            return new OneTimeTask();
        }
    },
    DAILY {
        @Override
        public Task createTask() {
            return new DailyTask();
        }
    },
    WEEKLY {
        @Override
        public Task createTask() {
            return new WeeklyTask();
        }
    },
    MONTHLY {
        @Override
        public Task createTask() {
            return new MonthlyTask();
        }
    },
    YEARLY {
        @Override
        public Task createTask() {
            return new YearlyTask();
        }
    };

    public abstract Task createTask();
}
