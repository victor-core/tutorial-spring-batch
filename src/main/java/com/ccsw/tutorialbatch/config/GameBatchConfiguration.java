package com.ccsw.tutorialbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.ccsw.tutorialbatch.model.Game;
import com.ccsw.tutorialbatch.model.GameExport;
import com.ccsw.tutorialbatch.processor.GameItemProcessor;

@Configuration
public class GameBatchConfiguration {

    @Bean
    public ItemReader<Game> readerGame(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Game>().name("gameItemReader").dataSource(dataSource)
                .sql("SELECT id, title, age_recommended, stock FROM game")
                .rowMapper((rs, rowNum) -> new Game(rs.getLong("id"), rs.getString("title"),
                        rs.getInt("age_recommended"), rs.getInt("stock")))
                .build();
    }

    @Bean
    public ItemProcessor<Game, GameExport> processorGame() {
        return new GameItemProcessor();
    }

    @Bean
    public ItemWriter<GameExport> writerGame() {
        return new FlatFileItemWriterBuilder<GameExport>().name("writerGame")
                .resource(new FileSystemResource("target/test-outputs/game-output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    @Bean
    public Step step1Game(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            ItemReader<Game> readerGame, ItemProcessor<Game, GameExport> processorGame,
            ItemWriter<GameExport> writerGame) {
        return new StepBuilder("step1Game", jobRepository).<Game, GameExport>chunk(10, transactionManager)
                .reader(readerGame).processor(processorGame).writer(writerGame).build();
    }

    @Bean
    public Job jobGame(JobRepository jobRepository, Step step1Game) {
        return new JobBuilder("jobGame", jobRepository).incrementer(new RunIdIncrementer()).flow(step1Game).end()
                .build();
    }
}
